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




