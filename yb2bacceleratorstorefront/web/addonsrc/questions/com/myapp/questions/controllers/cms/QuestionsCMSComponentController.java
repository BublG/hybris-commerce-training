package com.myapp.questions.controllers.cms;

import com.myapp.questions.model.cms2.components.QuestionsCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller("QuestionsCMSComponentController")
@RequestMapping(value = "/view/QuestionsCMSComponentController")
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionsCMSComponentModel> {
    private static final String FONT_SIZE_ATTRIBUTE = "fontSize";
    private static final String NUMBER_OF_QUESTION_TO_SHOW_ATTRIBUTE = "numberOfQuestionsToShow";
    private static final String QUESTIONS_ATTRIBUTE = "questions";

    private static final String PRODUCT_CODE_REQUEST_ATTRIBUTE = "productCode";

    @Resource(name = "productVariantFacade")
    private ProductFacade productFacade;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionsCMSComponentModel component) {
        List<ProductOption> questionsOption = Arrays.asList(ProductOption.QUESTIONS);
        ProductData product = productFacade.getProductForCodeAndOptions(
                (String)request.getAttribute(PRODUCT_CODE_REQUEST_ATTRIBUTE), questionsOption);
        model.addAttribute(QUESTIONS_ATTRIBUTE, product.getQuestions());
        model.addAttribute(NUMBER_OF_QUESTION_TO_SHOW_ATTRIBUTE, component.getNumberOfQuestionsToShow());
        model.addAttribute(FONT_SIZE_ATTRIBUTE, component.getFontSize());
    }
}
