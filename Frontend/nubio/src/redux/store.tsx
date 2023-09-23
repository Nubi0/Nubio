import { configureStore } from "@reduxjs/toolkit";
import kakaoSearch from "../redux/slice/KakaoSlice";
import EnjoySlice from "./slice/EnjoySlice";
import Profileslice from "./slice/Profileslice";
import SignUpSlice from "./slice/SignUpSlice";

export const store = configureStore({
  reducer: {
    search: kakaoSearch,
    enjoy: EnjoySlice,
    profile: Profileslice,
    signup: SignUpSlice,
  },
});
