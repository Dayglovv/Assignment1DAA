import csv
import os
import matplotlib.pyplot as plt


# Find project foldeR

project_folder = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

# CSV file
filename = os.path.join(project_folder,"results","results.csv")

# Read CSV

data = []

with open(filename, "r") as file:
    reader = csv.DictReader(file)
    for row in reader:
        data.append(row)

print("CSV loaded successfully!")
print("Rows:", len(data))

algorithms = ["MergeSort","QuickSort","Select","ClosestPair"]

input_types = ["random","sorted","reverse","duplicates"]


# Create plots folder

plots_folder = os.path.join(project_folder,"plots")

os.makedirs(plots_folder, exist_ok=True)

# Time graphs

for input_type in input_types:
    plt.figure()

    for algorithm in algorithms:
        x = []
        y = []

        for row in data:
            if (row["algorithm"] == algorithm
                    and row["inputType"] == input_type):
                x.append(int(row["inputSize"]))
                y.append(int(row["timeNs"]))

        if len(x) > 0:
            plt.plot(x,y,marker="o",label=algorithm)
    plt.xlabel("Input size")
    plt.ylabel("Time (ns)")
    plt.title("Time vs Input Size - "+ input_type)

    plt.legend()
    plt.grid(True)

    output_file = os.path.join(plots_folder,"time_" + input_type + ".png")

    plt.savefig(output_file)
    plt.close()

    print(
        "Saved:",
        output_file
    )

# Recursion depth graphs

for input_type in input_types:
    plt.figure()

    for algorithm in algorithms:
        x = []
        y = []
        for row in data:
            if (row["algorithm"] == algorithm
                    and row["inputType"] == input_type):
                x.append(int(row["inputSize"]))
                y.append(int(row["recursionDepth"]))

        if len(x) > 0:
            plt.plot(x,y,marker="o",label=algorithm)

    plt.xlabel("Input size")
    plt.ylabel("Recursion depth")

    plt.title("Recursion Depth vs Input Size - "+ input_type)

    plt.legend()
    plt.grid(True)

    output_file = os.path.join(plots_folder,"recursion_" + input_type + ".png")

    plt.savefig(output_file)
    plt.close()

    print("Saved:",output_file)


print()
print("All graphs created successfully!")