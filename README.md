# datToXML
Simple tool to convert Spiral Knights binary .dat files to .xml files.

Uses the libraries shipped with the game to convert files.

# Installing the game libraries into a local Maven repo
Enter the path to your Spiral Knights folder at line 4 in the local_install.sh script and at line 34 in the App.java source file.
Optionally edit line 35 to specifiy the output directory.

Execute the local_install.sh script, then compile using mvn package.
Upon execution every .dat file is converted into a .xml file.