import { BrowserRouter, Routes, Route } from "react-router-dom";
import routes from "./router";
import { Provider } from "react-redux";
import { store } from "./redux/store";
function App() {
  return (
    <Provider store={store}>
      <div className="App" style={{ width: "430px", height: "932px" }}>
        <BrowserRouter>
          <Routes>
            {routes.map((e) => {
              return (
                <Route
                  key={e.path}
                  path={e.path}
                  element={<e.Component></e.Component>}
                />
              );
            })}
          </Routes>
        </BrowserRouter>
      </div>
    </Provider>
  );
}

export default App;
