# 2048

A recreation of the online game 2048. The objective of the game is to combine tiles incrementally by sliding the tiles up, left, down, or right with the w, a, s, or d keys respectively. However players must manage tiles carefully as the fixed 4x4 board may overcrowd with tiles...

GUI is made with Java Swing. Includes mostly all functionality from the original game except animations and playability after achieving 2048. Implemented with a two dimensional array. 

Colors of different tiles are modified from the original game to increase distinguishability. 

## Setup and Run Instructions

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed  
- (Optional) An IDE like **Eclipse** or **IntelliJ IDEA** for easier running and editing

---

### Running the Project

#### Option 1: Using Eclipse (recommended)

1. Open Eclipse.
2. Select **File > Import > Existing Projects into Workspace**.
3. Choose the root folder of this project.
4. Make sure the project appears in the Project Explorer.
5. Navigate to the `src` folder and find the main class file (Gaming.java)
6. Right-click the main class file and select **Run As > Java Application**.

---

#### Option 2: Using Command Line

1. Open a terminal or command prompt.
2. Navigate to the root folder of the project.
3. Compile all `.java` files in the `src` folder:
   ```bash
   javac -d bin src/*.java

