# 🛒 Java E-Commerce Shopping Cart System

A robust, feature-rich Desktop E-Commerce application built using **Java Swing (GUI)**. This project follows a structured architecture separating the user interface, business logic services, and database layers to simulate a real-world online shopping experience.

---

## 🎥 Project Demo
See the application in action:
* 📁 **`EcommerceShoppingCart(Demo).mp4`** (Available in the root directory of this repository)

---

## 🚀 Key Features

* **🔐 Authentication (`LoginGUI.java`, `User.java`):** User registration and secure login framework.
* **📦 Product Catalog (`Product.java`, `ProductService.java`):** Browse items, check specifications, and filter products dynamically.
* **🛒 Smart Cart (`CartItem.java`, `ShoppingCart.java`):** Add items to cart, modify quantities, and compute real-time bill checkout totals.
* **💳 Payment Gateway (`PaymentGUI.java`):** Simulated payment interface to securely process transactions.
* **📋 Order Management (`Order.java`, `OrderService.java`, `OrderHistoryGUI.java`):** Place orders, save transactional details, and check past order histories.
* **⭐ Product Reviews (`Review.java`, `ReviewService.java`):** Read and submit user feedback/ratings for products.

---

## 🛠️ Tech Stack Used

* **Language:** Java (JDK 8 or higher)
* **GUI Components:** Java Swing & AWT (Abstract Window Toolkit)
* **Database connectivity:** JDBC (Java Database Connectivity) via `DBConnection.java`
* **Target DB:** MySQL / SQLite *(Update based on your actual local database engine)*

---

## 📂 File Architecture & Directory Breakdown

Here is how the project files handle different layers of the application:

```text
├── Structural Entities (Models)
│   ├── User.java                # Customer profile parameters
│   ├── Product.java             # Individual product attributes
│   ├── CartItem.java            # Represents an item inside the shopping cart
│   ├── Order.java               # Order structural constraints
│   └── Review.java              # Product rating and feedback structures
│
├── Business Logic (Services)
│   ├── ProductService.java      # Handles inventory operations
│   ├── OrderService.java        # Manages tracking and checkout processes
│   └── ReviewService.java       # Controls submission and retrieval of feedback
│
├── Presentation Layer (GUI Screens)
│   ├── LoginGUI.java            # Entrance screen for user verification
│   ├── EcommerceGUI.java        # Primary marketplace hub dashboard
│   ├── PaymentGUI.java          # Digital payment handling window
│   └── OrderHistoryGUI.java     # Screen listing past checkout records
│
└── Database & Main Controller
    ├── DBConnection.java        # Global database driver setup
    ├── Main.java                # Absolute application bootstrap file
    └── README.md                # Project manuals & documentation
