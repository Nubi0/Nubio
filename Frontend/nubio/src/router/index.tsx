import HomePage from "../pages/HomePage"
import LoginPage from '../pages/LoginPage'
import EnjoyHomePage from "../pages/EnjoyHomePage";

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
    }
]

export default routes;