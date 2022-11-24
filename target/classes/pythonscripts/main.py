from parserr import Parserr
from pprint import pprint
import math
import time
import csv
import argparse

def mainf(modelname):
    parser = Parserr(modelname)
    start = time.time()
    pprint(parser.generate_initializations())

    # Grab Currrent Time After Running the Code
    end = time.time()

    # Subtract Start Time from The End Time
    total_time = end - start
    print(total_time)

    # print(parser.get_decision_data_attributes())
    number_of_xml_tags = parser.number_of_xml_tags()
    # print("Hello")

    statistics = [
        ['inni_gen_time', total_time],
        ['number_of_xml_tags', number_of_xml_tags],
        ['num_annot_elems', parser.number_of_annotated_elements()],
        ['data_attributes', len(parser.find_all(".//ownedAttribute"))],
        ['decision_data_attributes', len(parser.get_decision_data_attributes())],
        ['num_of_initializations', parser.number_of_inis],
    ]
    with open('statistics.csv', 'w', newline='') as f:
    # using csv.writer method from CSV package
        write = csv.writer(f)
        write.writerows(statistics)

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    argparser = argparse.ArgumentParser(description='Generates initializations')
    argparser.add_argument(
        '--model',
        metavar='path',
        required=True,
        help='The path to the model generated with Papyrus'
    )
    args = argparser.parse_args()
    mainf(args.model)










