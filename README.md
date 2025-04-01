# Big Data Projects – Haifa University

This repository includes two Big Data homework assignments from the Big Data course at Haifa University. These assignments focus on the practical implementation of distributed data processing using **Hadoop MapReduce** and **Apache Spark**.

---

## 📦 Homework 1 – Running MapReduce with Hadoop

### 🧠 Objectives
- Set up a local Hadoop cluster using Docker and Docker Compose.
- Understand the architecture of HDFS and MapReduce.
- Implement and run classic MapReduce jobs.

### 🚀 Tasks Implemented
1. **Distributed Grep**: Search for a string across a distributed dataset.
2. **Word Length Counter**: Calculate frequency distribution of word lengths.
3. (Other MapReduce tasks can be added if implemented.)

### 🛠 Technologies Used
- Docker & Docker Compose
- Apache Hadoop (HDFS, YARN)
- Java
- Maven

---

## 📦 Homework 2 – Analytics with Apache Spark

### 🧠 Objectives
- Analyze a real-world dataset using Apache Spark.
- Apply Spark transformations and actions for text analytics.
- Handle stop words and generate meaningful 2-grams.

### 📊 Tasks Implemented
1. Count of **alphabetic characters** (case-insensitive).
2. Count of **words** (punctuation removed).
3. Generation and counting of **2-grams**.
4. **Top 20 most frequent words** excluding stop words.
5. **Stylistic summary** of Trump’s tweets.

### 🛠 Technologies Used
- Apache Spark
- Python (PySpark)
- Stop-words NLP library
- Jupyter Notebook or command-line Spark scripts

---

## 📁 Project Structure

```bash
.
├── homework1/                 # Hadoop + MapReduce (Java)
│   ├── docker-hadoop/        # Docker Compose setup
│   ├── src/                  # Java code (MapReduce implementations)
│   └── README.md             # Instructions
│
├── homework2/                # Spark analytics (Python / PySpark)
│   ├── src/                  # Spark scripts or notebooks
│   └── trump_tweets.csv      # Dataset
│
└── README.md                 # This file
