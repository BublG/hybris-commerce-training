package com.myapp.questions.populators;

import com.myapp.questions.model.QuestionModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

import java.util.List;

public class CustomSearchResultVariantProductPopulator extends SearchResultVariantProductPopulator {
    private static final String QUESTIONS_FIELD = "questions";

    @Override
    public void populate(SearchResultValueData source, ProductData target) {
        super.populate(source, target);
        List<QuestionModel> questionModelList = (List<QuestionModel>) source.getValues().get(QUESTIONS_FIELD);
    }
}
