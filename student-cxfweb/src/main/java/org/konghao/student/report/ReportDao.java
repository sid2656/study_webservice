package org.konghao.student.report;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.konghao.student.dto.StudentExcelDto;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("reportDao")
public class ReportDao extends HibernateDaoSupport implements IReportDao {
	@Inject
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<StudentExcelDto> getReportStus() {
		//基于SQL查询
		String sql = "select name,xh,ksh,sex as 'xb'," +
				"(CASE status WHEN 0 THEN '在校' ELSE '离校' END) as 'status' from t_student";
		List stus = this.getSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.aliasToBean(StudentExcelDto.class))
				.list();
		return stus;
	}

}
