package TemplateDP;

abstract class ModelTrainer {
    public final void trainPipeLine(String dataPath) {
        loadData(dataPath);
        preProcessData();
        trainModel();
        evaluateModel();
        saveModel();
    }

    protected void loadData(String dataPath) {
        System.out.println("[Common] Loading dataset from " + dataPath);
    }

    protected void preProcessData() {
        System.out.println("[Common] Splitting into train/test and normalizing");
    }

    protected abstract void trainModel();

    protected abstract void evaluateModel();

    protected void saveModel() {
        System.out.println("[Common] Saving model to disk as default format");
    }

}

class NeuralNetworkTrainer extends ModelTrainer {
    @Override
    protected void trainModel() {
        System.out.println("[NeuralNet] Training Neural Network for 100 epochs");
        // pseudo-code: forward/backward passes, gradient descent...
    }

    @Override
    protected void evaluateModel() {
        System.out.println("[NeuralNet] Evaluating accuracy and loss on validation set");
    }

    @Override
    protected void saveModel() {
        System.out.println("[NeuralNet] Serializing network weights to .h5 file");
    }
}

class DecisionTreeTrainer extends ModelTrainer {
    // Use the default preprocessData() (train/test split + normalize)

    @Override
    protected void trainModel() {
        System.out.println("[DecisionTree] Building decision tree with max_depth=5");
        // pseudo-code: recursive splitting on features...
    }

    @Override
    protected void evaluateModel() {
        System.out.println("[DecisionTree] Computing classification report (precision/recall)");
    }
    // use the default saveModel()
}

public class TemplateMethodPattern {
    public static void main(String[] args) {
        System.out.println("=== Neural Network Training ===");
        ModelTrainer nnTrainer = new NeuralNetworkTrainer();
        nnTrainer.trainPipeLine("data/images/");

        System.out.println("\n=== Decision Tree Training ===");
        ModelTrainer dtTrainer = new DecisionTreeTrainer();
        dtTrainer.trainPipeLine("data/iris.csv");
    }
}
