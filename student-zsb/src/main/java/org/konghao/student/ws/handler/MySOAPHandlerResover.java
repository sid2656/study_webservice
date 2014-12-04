package org.konghao.student.ws.handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.springframework.stereotype.Service;

@SuppressWarnings("rawtypes")
@Service("mySOAPHandlerResover")
public class MySOAPHandlerResover implements HandlerResolver {
	private List<Handler> handlers;
	
	public MySOAPHandlerResover() {
		handlers = new ArrayList<Handler>();
		handlers.add(new LicenseHandler());
	}

	public List<Handler> getHandlerChain(PortInfo arg0) {
		return handlers;
	}

}
