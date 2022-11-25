<<<<<<< HEAD
### This repository is for Windows users. For Linux users, see branch linux
# MBFair
This repository implements the MBFair methodology according to Qusai et al. This methodology helps check for fairness of a system given its finite-state model using linear temporal logic.
## Requirements
- The [Spin Model Checker](https://spinroot.com/spin/whatispin.html). After installing the spin model checker, verify that the model checker is callable from the command line using the command ``` spin -V ``` . You should have the version of the model checker printed in the console after executing the command on the command line or terminal
- Any java version greater than 11
- Any release of python3
- A modeled finite state system using [Eclipse Papyrus](https://www.eclipse.org/papyrus/)
## Installation
- Clone this git repository and locate the file /target/mbfair.jar
- The file is callable using the command in the Usage section below
- Include the folders hugort-0_8a in your project root directory
If you completed the previous steps successfully, you should be able to use the tool using the commands in the next section

## Usage
### 1. Generating initializations
This first step is to generate initializations. You can do that using the following command
```
java -jar mbfair-0.0.1.jar -g -f="absolute-path-to-file"

```
In the above command, replace the term <strong><ins>absolute-path-to-file</ins></strong> with the absolute path to your system model with Papyrus

### 2. Checking for fairness.

Upon generating initializations, csv files will be automatically created by the tool. The tool uses these csv files. To specify protected characteristics and proxies of protected characteristics, modify the generated file <strong>initializations_table.csv</strong>. Consider the csv file as a table where the first row in the csv represented the protected characteristics and any subsequent row data represent the proxies of the corresponding protected characteristics.

After modifying the file, run the following command
```
java -jar target/mbfair-0.0.1.jar -r -f="absolute-path-to-file"
```
Replace the string absolute-path-to-file as in step one

The model runs and the results of the check are printed in the console and saved in <strong>statistics.csv</strong>.

You can use the --help commmand below to see the list of acceptable commands

```
java -jar mbfair-0.0.1.jar --help
```


=======
# MBFair: A Model-based Verification Methodology for Detecting Violations of Individual Fairness

In this file, we present the artifact used in our paper. The submission includes:
* the *UMLfair profile*.

* The MBFair tool.

* The UML models of three case studies namely, the Bank Management System based on the description of a decision-making process in a [banking system] (https://www.hypovereinsbank.de/hvb/privatkunden/services-und-marktinformationen/kundenprogramm-valyou), the Delivery Management System based on the incedent describtion of [Amazon's delivery-free service](https://www.bloomberg.com/graphics/2016-amazon-same-day/), and the Loan Management System based on [business process model](https://link.springer.com/chapter/10.1007/978-3-319-92901-9_19) from an [event log](https://www.win.tue.nl/bpi/doku.php?id=2012:challenge) recording the loan management process of a Dutch financial institute. 

# Recources

* **Profile: The UMLfair Profile:** https://github.com/confFair/project/blob/master/profile.zip
* **Source code of our MBFair tool**
* **Artifacts: The bank management system case study:** 
* **Artifacts: The delivery management system case study:** 
* **Artifacts: The loan management system case study:** 

The artifacts of each each case study contains: 
* A (*.uml*) file: the UML model of the system annotated with the UMLfair profile.
* A (*.xlsx*) file: the dataset of the system which used to uncover proxies for protected data. 

# How to install
>>>>>>> d32d688ddc2f4687df4db9500908e5d3a481c221


