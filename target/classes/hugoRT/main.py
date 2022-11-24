from parserr import Parser
from pprint import pprint
import math
import time
import csv

# Number of attributes = number of ownedAttribute elements
# The number of annotated elements is the number of elements annotated with UMLFairness:Critical


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    parser = Parser("amazon.xml")
    start = time.time()
    pprint(parser.generate_initializations())

    # Grab Currrent Time After Running the Code
    end = time.time()

    # Subtract Start Time from The End Time
    total_time = end - start
    print(total_time,"initializations generation time")
    statistics = [total_time]
    with open('statistics.csv',newline='') as f:
        r = csv.reader(f)
        data = [line for line in r]
        data.append(["Initialization_Generation_Time", total_time])
        print("file data",data)

    with open('statistics.csv', 'w', newline='') as f:
        # using csv.writer method from CSV package
        write = csv.writer(f)
        for row in data:
            write.writerow(row)