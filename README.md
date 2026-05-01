# Ledger App

## Description of the Project

Ledger App is a simple bank ledger application made with Java and JavaFX. Users can track their
deposits and payments, view their full transaction history. They can generate filtered reports by
date range, vendor, or their own custom criteria. Transactions are sourced through local transactions.csv.

The Goal of App is users who want something simple to keep track of deposits and payments. People who
like simple filters to hold their personal income and expenses in check without having scattered receipts.

The original project began as a CLI application, but then later was adapted to have a GUI made with JavaFX
to make it even simpler for users to use the app.

## User Stories

- Task: Create a Transaction class to represent a single ledger entry.
- Task: Read and save all saved transactions from transactions.csv onto our transactions ArrayList.
- As an account holder, I want to add a deposit and have the option to add more, so that I can quickly enter several at
  once without bouncing back to the home menu.
- As an account holder, I want to record a payment and have the option to add more, so that I can log several expenses
  in a row without going back to the menu each time.
- As an account holder, I want to exit the app cleanly from Home screen, so that nothing is left hanging when I'm done.
- As an account holder, I want to view all my transactions in one list, so that I can see my full history at a glance.
- As an account holder, I want to view only my deposits, so that I can quickly check my income without other entries
  cluttering the screen.
- As an account holder, I want to view only my payments, so that I can review my spending without my deposits getting in
  the way.
- As an account holder, I want to return to the Home Screen from the Ledger, so that I can add new transactions or close
  the app.
- As an account holder, I want to run a Month-to-Date report, so that I can see how I'm doing this month so far.
- As an account holder, I want to run a Previous Month report, so that I can review last month's numbers in one place.
- As an account holder, I want to run a Year-to-Date report, so that I can see how the year is shaping up.
- As an account holder, I want to run a Previous Year report, so that I can review last year's full activity.
- Task: Fill in filterTransactionsByVendor(String vendor) so the search-by-vendor report has its own clean place to
  live.
- As an account holder, I want to search the ledger by vendor name, so that I can see everything I've spent at one
  specific business.
- Task: Fill in filterTransactionsByDate(LocalDate start, LocalDate end) so all four date-range reports can share one
  printer instead of repeating the same loop.
- As an account holder, I want a custom search across multiple fields, so that I can find exactly the transaction I'm
  looking for.
- Task: Fill in parseDate to handle prevent any parsing errors.

## Setup

Instructions on how to set up and run the project using IntelliJ IDEA.

### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download
  from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.
- Javafx-controls: Dependency
- Javafx-fxml: Dependency
- Javafx-maven-plugin: 0.0.8

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, Wait for IntelliJ to import the Maven project and download dependencies (you'll see progress
   at the bottom).
4. Find the main class with the `public class Main extends Application` method.
5. In the Maven tool window (right sidebar), run Lifecycle → install — or just run mvn clean install in the terminal.
6. Then in the sidebar click on Execute Maven Goal and then search mvn clean javafx:run to run project.

## Technologies Used

- Java: 17
- Javafx-controls: 21.0.11
- Javafx-fxml: 21
- Javafx-maven-plugin: 0.0.8

## Demo

HomeScreen

<img width="597" height="427" alt="Screenshot 2026-05-01 at 5 22 42 AM" src="https://github.com/user-attachments/assets/72131925-fa24-4289-8e92-747a474a2d3d" />

Deposit Screen

<img width="593" height="425" alt="Screenshot 2026-05-01 at 5 22 55 AM" src="https://github.com/user-attachments/assets/8f1abf2f-3bd5-45e0-b77d-fdafa09dfccb" />

Payment Screen

<img width="592" height="430" alt="Screenshot 2026-05-01 at 5 23 12 AM" src="https://github.com/user-attachments/assets/e3a68e91-17f3-437d-9e2f-f7b6c8c4a6c4" />

Ledger Screen

<img width="599" height="433" alt="Screenshot 2026-05-01 at 5 23 27 AM" src="https://github.com/user-attachments/assets/d3c0e6e1-1796-4178-9738-8d3366bb1dc5" />

Report Screen

<img width="592" height="424" alt="Screenshot 2026-05-01 at 5 59 02 AM" src="https://github.com/user-attachments/assets/91449330-447d-4049-8d90-1c65fa9be8cd" />

Table Format

<img width="1000" height="426" alt="Screenshot 2026-05-01 at 5 25 53 AM" src="https://github.com/user-attachments/assets/aae8b696-41c2-4fc6-b2fe-5f65e94b2efe" />


Optional Alerts

<img width="607" height="431" alt="Screenshot 2026-05-01 at 5 24 26 AM" src="https://github.com/user-attachments/assets/d837d2e9-b7b7-4b77-8e09-8610925c26d9" />
<img width="598" height="429" alt="Screenshot 2026-05-01 at 5 24 08 AM" src="https://github.com/user-attachments/assets/951ca72f-1b4f-491b-9570-fd1c5273fb88" />





## Future Work

Outline potential future enhancements or functionalities you might consider adding:

- Would love to include CSS.
- Design structure of project better.
- Provide more comments.
- Add user stories for extra activity as well.

## Resources

List resources such as tutorials, articles, or documentation that helped you during the project.

- [Java Programming Tutorial](https://www.example.com)
- [Effective Java](https://www.example.com)
- https://openjfx.io/openjfx-docs/#maven
- https://gluonhq.com/products/javafx/
- https://www.youtube.com/watch?v=9XJicRt_FaI&t=14089s
- https://www.youtube.com/watch?v=3eE-xz-l2mY
- https://www.youtube.com/@thenewboston/search?query=javafx

## Thanks

Express gratitude towards those who provided help, guidance, or resources:

- Thank you to Raymond for continuous support and guidance.
- A special thanks to all teammates for their dedication and teamwork.
 
