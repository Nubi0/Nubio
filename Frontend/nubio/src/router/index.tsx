import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import EnjoyHomePage from '../pages/EnjoyHomePage';
import SignUpPage from '../pages/SignUpPage';
import AllCoursePage from '../pages/AllCoursePage';
import ProfilePage from '../pages/ProfilePage';
import CourseDetailPage from '../pages/CourseDeatilPage';
import LikePage from '../pages/LikePage';
import MyCoursePage from '../pages/MyCoursePage';
import PlaceDetailPage from '../pages/PlaceDetailPage';
import RecommendPage from '../pages/RecommendPage';
import SafeHomePage from '../pages/SafeHomePage';
import CourseSelectPage from '../pages/CourseSelectPage';
import KaKaoLogin from '../pages/KaKaoLogin';
import CourseLocationSelectPage from '../pages/CourseLocationSelectPage';
import AllCourseLocationSelectPage from '../pages/AllCourseLocationSelectPage';

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
    path: '/signUp',
    Component: SignUpPage,
  },
  {
    path: '/enjoy/all',
    Component: AllCoursePage,
  },
  {
    path: '/profile',
    Component: ProfilePage,
  },
  {
    path: '/coursedetail',
    Component: CourseDetailPage,
  },
  {
    path: '/like',
    Component: LikePage,
  },
  {
    path: '/mycourse',
    Component: MyCoursePage,
  },
  {
    path: '/placedetail',
    Component: PlaceDetailPage,
  },
  {
    path: '/recommend',
    Component: RecommendPage,
  },
  {
    path: '/safe',
    Component: SafeHomePage,
  },
  {
    path: 'enjoy/mycourse/select',
    Component: CourseSelectPage,
  },
  {
    path: '/oauth/kakao',
    Component: KaKaoLogin,
  },
  {
    path: '/enjoy/mycourse/location/select',
    Component: CourseLocationSelectPage,
  },
  {
    path: '/enjoy/all/location',
    Component: AllCourseLocationSelectPage,
  }
];

export default routes;
