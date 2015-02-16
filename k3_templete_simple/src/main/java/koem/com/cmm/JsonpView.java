package koem.com.cmm;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.Gson;

@Component
public class JsonpView extends AbstractView {

	private final String DEFAULT_CHAR_ENCODING = "utf-8";
	private final String DEFAULT_CONTENT_TYPE = "text/html";
//	private final String DEFAULT_CONTENT_TYPE = "application/javascript";
	
	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(DEFAULT_CONTENT_TYPE);
		response.setCharacterEncoding(DEFAULT_CHAR_ENCODING);
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)	throws Exception {
		PrintWriter out = response.getWriter();
		String result = "";
		if(model.containsKey("data")){
			result = new Gson().toJson(model.get("data"));
		}else{
			HashMap<String, Object> jsonMap = new  HashMap<String, Object>();
			Set<String> keySet = model.keySet();
			for (String k : keySet) {
//				if(!k.startsWith("org.springframework")){
 					jsonMap.put(k, model.get(k));
//				}
			}
			result = new Gson().toJson(jsonMap);
		}
		String callback = request.getParameter("callback")  == null ?  "callback" : request.getParameter("callback");
		out.print(callback+"("+result+")");
	}

}
