import HomePage from "../pages/HomePage"
import LoginPage from '../pages/LoginPage'
import EnjoyHomePage from "../pages/EnjoyHomePage";
import SignUpPage from "../pages/SignUpPage";

const routes = [
    {
        path: '/',
        Component: HomePage,
    },
    {
        path: '/login',
        Component: LoginPage,
    },
    {
        path: '/enjoy',
        Component: EnjoyHomePage,
    },
    {
        path: "/signUp",
        Component: SignUpPage,
    },
]

export default routes;
