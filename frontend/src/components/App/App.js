import logo from '../../logo.svg';
import './App.css';
import React, {Component} from "react";
import Books from "../Books/BookList/books";
import Categories from "../Authors/authors"
import bookService from "../../repository/bookRepository";
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import Header from '../Header/header';
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
        books: [],
        authors: [],
        selectedBook: {}
    }
  }

  render() {
    return(
        // <div>
        //   <Books books={this.state.books}/>
        // </div>
        <Router>

            <Header/>
                <main>
                    <div className="container">

                        <Route path={"/books/add"} exact render={() => <BookAdd authors={this.state.authors} onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit authors={this.state.authors}
                                      onEditBook={this.editBook}
                                      book={this.state.selectedBook}
                                      />}/>
                        <Route path={"/books"} render={() =>
                            <Books books={this.state.books}
                                   onDelete={this.deleteBook}
                                   onEdit={this.getBook}
                                   onTake={this.takeBook}

                            />}/>
                        <Redirect to={"/books"}/>
                    </div>
                </main>
        </Router>
    );
  }


    componentDidMount() {
        this.loadBooks();
        this.loadAuthors()
    }


    loadBooks = () => {
        bookService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }



    loadAuthors = () => {
        bookService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    deleteBook = (id) => {
      bookService.deleteBook(id)
          .then(() => {
              this.loadBooks();
          })
    }

    takeBook = (id) => {
        bookService.takeBook(id)
            .then(() => {
                this.loadBooks();
            })
    }

    addBook = (name, category, author, availableCopies) => {
      bookService.addBook(name, category, author, availableCopies)
          .then(() => {
              this.loadBooks();
          })
    }

    getBook = (id) => {
      bookService.getBook(id)
          .then((data) => {
              this.setState({
                  selectedBook: data.data
              })

          })
    }


    editBook = (id, name, category, author, availableCopies) => {
        bookService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }




}







// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }


export default App;
