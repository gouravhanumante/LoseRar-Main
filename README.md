# 🚀 LoseRar

![License](https://img.shields.io/badge/license-MIT-green) ![Java](https://img.shields.io/badge/Java-17-orange)

**LoseRar** is a lightweight Java command-line application for compressing and extracting files to help save storage space efficiently — no GUI, just pure code doing the job.

---

## ✨ Features

- 🗂️ Compress files into common archive formats (ZIP, RAR, etc.)  
- 📂 Extract files from archives  
- ⚡ Fast and reliable compression and extraction  
- 🛠️ Simple command-line usage — perfect for automation or scripting  
- 📦 Supports multiple file formats  

---

### Prerequisites

- Java 17 or higher installed  

### 🤝 Contributing
Feel free to fork and submit pull requests!
Please include tests and update documentation when possible.


## 🧠 Huffman Coding Algorithm

**Huffman Coding** is a popular lossless data compression algorithm that assigns variable-length binary codes to input characters. Characters with higher frequency get shorter codes, reducing the overall data size.

---

### How It Works

1. **Calculate Frequency**  
   Count the frequency of each character in the input data.

2. **Build Priority Queue**  
   Create a min-heap priority queue containing nodes with characters and their frequencies.

3. **Build Huffman Tree**  
   - While more than one node remains in the queue:  
     - Extract the two nodes with the lowest frequency.  
     - Create a new internal node with these two as children.  
     - The new node's frequency = sum of the two nodes' frequencies.  
     - Insert the new node back into the queue.

4. **Generate Codes**  
   Traverse the tree from root to leaves, assigning:  
   - `'0'` for left edge  
   - `'1'` for right edge  
   The path to each leaf node is the character’s Huffman code.

5. **Encode Data**  
   Replace each character in the input by its corresponding Huffman code.

---

### Example Huffman Codes

| Character | Frequency | Huffman Code |
| --------- | --------- | ------------ |
| A         | 5         | 0            |
| B         | 9         | 101          |
| C         | 12        | 100          |
| D         | 13        | 111          |
| E         | 16        | 110          |

> *Note: Actual codes depend on the tree structure based on character frequencies.*


### 🎯 Usage
```bash
java -cp bin com.yourpackage.MainClass compress /path/to/files output.zip
java -cp bin com.yourpackage.MainClass extract archive.zip /output/directory

