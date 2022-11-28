from parser import Parser
from pprint import pprint
import math
import time
import csv
import argparse

def mainf(modelname):
    parser = Parser(modelname)
    start = time.time()
    pprint(parser.generate_initializations())

    # Grab Currrent Time After Running the Code
    end = time.time()

    # Subtract Start Time from The End Time
    total_time = end - start
    print()
    print("Time taken to generate initializations:")
    print(total_time)
    print()
    # print(parser.get_decision_data_attributes())
    number_of_xml_tags = parser.number_of_xml_tags()
    # print("Hello")

    use_case_name = parser.find(query=".//uml:Model", namespaces=parser.namespace()).get("name")
    statistics = [
        ['inni_gen_time', total_time],
        ['number_of_xml_tags', number_of_xml_tags],
        ['num_annot_elems', parser.number_of_annotated_elements()],
        ['data_attributes', len(parser.find_all(".//ownedAttribute"))],
        ['decision_data_attributes', len(parser.get_decision_data_attributes())],
        ['num_of_initializations', parser.number_of_inis],
        ['use_case_name', use_case_name]
    ]
    with open('statistics.csv', 'w') as f:
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










