# 🎬 Movie Review System – Practical Java Project

This repository contains my final **practical project** for the **Structured Programming** module of the **Software Developer** course at **CESAE Digital**, under the guidance of **Professor Vítor Santos**.

---

## 🧠 Module Overview

- **Module**: Programação Estruturada (Structured Programming)  
- **Language**: Java  
- **Instructor**: Vítor Santos  
- **Focus**: Procedural programming logic, file handling, input validation, menu systems, and modularity using functions

---

## 🎯 Project Overview

After completing the Software Developer course, the fictitious company **IMDV©**, a film review platform, hired me to build a system capable of managing, displaying, and analyzing movie reviews.

The program reads data from CSV files and includes a full-featured user experience for both **Admins** and **Clients**, with login validation, conditional menus, and advanced operations.

---

## 🧩 Main Features

### 👤 User Selection

- Program starts by asking for user type: `ADMIN` or `CLIENT`
- Admins must log in using credentials stored in a CSV file
- Clients can register and browse content without login

---

### 🛠️ Admin Menu

1. View full CSV file content  
2. Count total ratings  
3. Display all reviewed studios (no duplicates)  

---

### 🎟️ Client Menu

1. Register new user (username, contact, email)  
2. View full movie catalog with ratings  
3. View catalog of graphical/visual movies (e.g., *Interstellar*, *Star Wars*)  
4. Display the highest-rated studio  
5. Display the lowest-rated studio  
6. View most recent review  
7. Take a movie quiz based on CSV questions (scored)  
8. Search by Studio → show all genres and movies by that studio  
9. Search by Genre → show all studios and related movies  

---

## 🗂️ File Handling

- **movies.csv** → stores movie data (ID, title, rating, duration, year, studio, director, genre)  
- **logins.csv** → stores admin credentials (username, password)  
- The program includes robust error handling and input validation to ensure usability and stability

---

## 💬 Example Interaction

```text
> User Type (ADMIN || CLIENT): ADMIN  
> USERNAME: chefe  
> PASSWORD: pass123  
> Incorrect password  
>  
> USERNAME: chefe  
> PASSWORD: !password?456  
> Login successful  
>  
> ADMIN MENU  
> 1. View File  
> 2. Count Ratings  
...
