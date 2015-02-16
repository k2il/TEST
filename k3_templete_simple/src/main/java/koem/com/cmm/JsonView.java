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
import com.google.gson.GsonBuilder;

@Component
public class JsonView extends AbstractView {

	private final String DEFAULT_CHAR_ENCODING = "utf-8";
	//private final String DEFAULT_CONTENT_TYPE = "application/json";
	private final String DEFAULT_CONTENT_TYPE = "text/html"; // IE 에서는 application/json 을 인식하지 않음. IE-9 버전 이상에서 application/json 정상동작.

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(DEFAULT_CONTENT_TYPE);
		response.setCharacterEncoding(DEFAULT_CHAR_ENCODING);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)	throws Exception {
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		if(model.containsKey("data")){
			out.print(gson.toJson(model.get("data")));
		}else{
			HashMap<String, Object> jsonMap = new  HashMap<String, Object>();
			Set<String> keySet = model.keySet();
			for (String k : keySet) {
//				if(!k.startsWith("org.springframework")){
 					jsonMap.put(k, model.get(k));
//				}
			}
			out.print(gson.toJson(jsonMap));
		}
	}

}
