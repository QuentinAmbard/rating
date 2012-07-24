package org.avricot.rating.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * Shared WebBindingInitializer for Date & String.
 * 
 * <p>
 * Alternatively, such init-binder code may be put into
 * {@link org.springframework.web.bind.annotation.InitBinder} annotated methods
 * on the controller classes themselves.
 */
public class DefaultBindingInitializer implements WebBindingInitializer {

    public void initBinder(final WebDataBinder binder, final WebRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
