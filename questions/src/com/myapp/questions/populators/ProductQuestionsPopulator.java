package com.myapp.questions.populators;

import com.myapp.questions.data.QuestionData;
import com.myapp.questions.model.QuestionModel;
import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.stream.Collectors;

public class ProductQuestionsPopulator<SOURCE extends ProductModel, TARGET extends ProductData> extends AbstractProductPopulator<SOURCE, TARGET> {

    @Override
    public void populate(SOURCE source, TARGET target) throws ConversionException {
        target.setQuestions(source.getQuestions().stream()
                .map(this::convertQuestionModelToData)
                .collect(Collectors.toList()));
    }

    private QuestionData convertQuestionModelToData(QuestionModel questionModel) {
        QuestionData questionData = new QuestionData();
        questionData.setQuestion(questionModel.getQuestion());
        questionData.setAnswer(questionModel.getAnswer());
        return questionData;
    }
}
