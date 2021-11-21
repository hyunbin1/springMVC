package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class ModelView {

    // 뷰의 논리적 이름
    private String viewName;
    // 모델 관련된 내용
    private Map<String, Object> model = new HashMap<>();

    // 생성자
    public ModelView(String viewName){
        this.viewName = viewName;
    }

    // getter, setter
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    public Map<String, Object> getModel() {
        return model;
    }
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }



}
