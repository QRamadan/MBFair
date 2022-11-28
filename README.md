### This repository is for linux users. For Windows users, see [branch windows](https://github.com/QRamadan/MBFair/tree/windows)
# MBFair
This repository implements the MBFair methodology according to Qusai et al. This methodology helps check for fairness of a system given its finite-state model using linear temporal logic. For more information, see the [main repository](https://github.com/QRamadan/MBFair/tree/master)
## Requirements
The following list of requirements must be satisfied before the tool can be installed
- A modeled finite state system using [Eclipse Papyrus](https://www.eclipse.org/papyrus/)
- The [Spin Model Checker](https://spinroot.com/spin/whatispin.html) must be installed. Installation steps are available [here](https://spinroot.com/spin/Man/README.html#S1). After installing the spin model checker, verify that the model checker is callable from the command line using the command ```spin -V``` . You should have the version of the model checker printed in the console after executing the command on the command line or terminal
- Any java version greater than 11 is required. Various java version and installation instructions can be found [here](https://docs.oracle.com/en/java/javase/index.html). After installing java, verify that the installation was successful using the command ```java --version```. You should see the version of the currently installed JAVA printed in the console.
- Any release of PYTHON3 is also required. Installation instructions can be found [here](https://www.scaler.com/topics/python/install-python-on-linux/). After installing python3, verity that the installation was successful by running the command ```python3 --version```. You should see the version of the currently installed PYTHON3 printed in the console.
## Installation
- Clone this git repository and locate the file /target/mbfair-0-0.1.jar
- The file is callable using the commands in the [Usage](#usage) section below
- Copy the folders hugort-0_8a and pythonscripts in your project's root directory
If you completed the previous steps successfully, you should be able to use the tool using the commands in the next section

## <section id="usage"> Usage </section>
### 1. Generating initializations
This first step is to generate initializations. You can do that using the following command
```
java -jar mbfair-0.0.1.jar -g -f="absolute-path-to-file"
```
In the above command, replace the term <strong><ins>absolute-path-to-file</ins></strong> with the absolute path to your system model with Papyrus

### 2. Checking for fairness.

Upon generating initializations, csv files will be automatically created by the tool. The tool uses these csv file. To specify protected characteristics and proxies of protected characteristics, modify the generated file <strong>initializations_table.csv</strong>. Consider the csv file as a table where the first row in the csv represented the protected characteristics and any subsequent row data represent the proxies of the corresponding protected characteristics.

For example, consider that you have a use case with two protected characteristics - char1 and char2. Char1 has one two proxies Char1_prox_1 and Char1_prox_2. Char2 has one proxy Char2_prox_1. This information will be represented in the csv as follows
```
Char1,Char2
Char1_prox_1,Char2_prox_1
Char1_prox_2,
```

After modifying the file, save the file and run the following command
```
java -jar target/mbfair-0.0.1.jar -r -f="absolute-path-to-file"
```
Replace the string absolute-path-to-file as in step one

The model runs and the results of the check are printed in the console




