import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.Id3;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class maskinlæring {
  public static void main(String args[]) throws Exception {
    Id3 decTree = new Id3();

    MultilayerPerceptron linClassifier = new MultilayerPerceptron();
    linClassifier.setHiddenLayers("0");

    DataSource source = new DataSource("party.arff");

    Instances instances = source.getDataSet();

    instances.setClassIndex(instances.numAttributes() - 1);

    Instances testSet = new Instances(instances);

    Instance newInst = new Instance(instances.numAttributes());
    newInst.setDataset(testSet);
    Attribute age = instances.attribute(0);
    Attribute gender = instances.attribute(1);
    Attribute faculty = instances.attribute(2);
    Attribute culture = instances.attribute(3);
    Attribute outgoing = instances.attribute(4);
    Attribute sporting = instances.attribute(5);
    Attribute alcohol = instances.attribute(6);
    Attribute smoker = instances.attribute(7);
    Attribute classification = instances.attribute(8);

    newInst.setValue(age, "old");
    newInst.setValue(gender, "woman");
    newInst.setValue(faculty, "medicine");
    newInst.setValue(culture, "low-culture");
    newInst.setValue(outgoing, "yes");
    newInst.setValue(sporting, "no");
    newInst.setValue(alcohol, "some");
    newInst.setValue(smoker, "yes");

    decTree.buildClassifier(testSet);

    double classificationNumeric = decTree.classifyInstance(newInst);
    System.out
        .println(classificationNumeric + " " + classification.value((int) classificationNumeric));


  }
}
