import HomePage from "../pages/HomePage"
import LoginPage from '../pages/LoginPage'

const routes = [
    {
        path: '/',
        Component: HomePage,
    },
    {
        path: '/login',
        Component: LoginPage,
    }
]

export default routes;