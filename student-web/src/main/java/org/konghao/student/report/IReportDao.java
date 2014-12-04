package org.konghao.student.report;

import java.util.List;

import org.konghao.student.dto.StudentExcelDto;

public interface IReportDao {
	public List<StudentExcelDto> getReportStus();
}
