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
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method.
5. Right-click on the file and select 'Run 'YourMainClassName.main()'' to start the application.

## Technologies Used

- Java: 17
- Javafx-controls: 21.0.11
- Javafx-fxml: 21
- Javafx-maven-plugin: 0.0.8

## Demo

Include screenshots or GIFs that show your application in action. Use tools
like [Giphy Capture](https://giphy.com/apps/giphycapture) to record a GIF of your application.
![Screenshot 2026-05-01 at 5.22.42 AM.png](../../../Screenshot%202026-05-01%20at%205.22.42%E2%80%AFAM.png)
![Screenshot 2026-05-01 at 5.22.55 AM.png](../../../Screenshot%202026-05-01%20at%205.22.55%E2%80%AFAM.png)
![Screenshot 2026-05-01 at 5.59.02 AM.png](../../../Screenshot%202026-05-01%20at%205.59.02%E2%80%AFAM.png)
![Screenshot 2026-05-01 at 5.23.12 AM.png](../../../Screenshot%202026-05-01%20at%205.23.12%E2%80%AFAM.png)
![Screenshot 2026-05-01 at 5.23.27 AM.png](../../../Screenshot%202026-05-01%20at%205.23.27%E2%80%AFAM.png)
![Screenshot 2026-05-01 at 5.25.53 AM.png](../../../Screenshot%202026-05-01%20at%205.25.53%E2%80%AFAM.png)
![Screenshot 2026-05-01 at 5.24.26 AM.png](../../../Screenshot%202026-05-01%20at%205.24.26%E2%80%AFAM.png)
![Screenshot 2026-05-01 at 5.24.08 AM.png](../../../Screenshot%202026-05-01%20at%205.24.08%E2%80%AFAM.png)

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
 