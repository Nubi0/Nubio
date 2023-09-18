import { configureStore } from "@reduxjs/toolkit";
import kakaoSearch from "../redux/slice/KakaoSlice";
import EnjoySlice from "./slice/EnjoySlice";
import Profileslice from "./slice/Profileslice";

export const store = configureStore({
  reducer: {
    search: kakaoSearch,
    enjoy: EnjoySlice,
    profile: Profileslice,
  },
});