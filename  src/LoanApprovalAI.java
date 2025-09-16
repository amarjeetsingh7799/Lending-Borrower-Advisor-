package com.lendingadvisor;

import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoanApprovalAI {
    J48 model;

    public void trainModel(String trainingDataPath) throws Exception {
        DataSource source = new DataSource(trainingDataPath);
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        model = new J48();
        model.buildClassifier(data);
    }

    public String approveLoan(Borrower borrower) throws Exception {
        if (model == null) {
            return "Model not trained. Call trainModel() first.";
        }
        Instance instance = createInstance(borrower);
        double prediction = model.classifyInstance(instance);
        return (prediction == 1.0) ? "Approved" : "Denied";
    }

    private Instance createInstance(Borrower borrower) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("loanAmount"));
        attributes.add(new Attribute("creditScore"));
        attributes.add(new Attribute("income"));
        attributes.add(new Attribute("existingDebt"));
        List<String> classValues = Arrays.asList("Denied", "Approved");
        attributes.add(new Attribute("class", classValues));
        Instances dataset = new Instances("TestInstance", attributes, 0);
        dataset.setClassIndex(4);
        Instance instance = new DenseInstance(5);
        instance.setValue(attributes.get(0), borrower.loanAmount);
        instance.setValue(attributes.get(1), borrower.creditScore);
        instance.setValue(attributes.get(2), borrower.income);
        instance.setValue(attributes.get(3), borrower.existingDebt);
        instance.setDataset(dataset);
        return instance;
    }
}
