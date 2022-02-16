package com.myapp.questions.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

public class CustomSearchResultVariantProductPopulator extends SearchResultVariantProductPopulator {
    private static final String QUESTIONS_COUNT_FIELD = "questionsCount";

    @Override
    public void populate(SearchResultValueData source, ProductData target) {
        super.populate(source, target);
        target.setQuestionsCount(this.<Integer> getValue(source, QUESTIONS_COUNT_FIELD));
    }
}
