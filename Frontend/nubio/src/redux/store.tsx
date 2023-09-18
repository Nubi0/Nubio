import { configureStore } from "@reduxjs/toolkit";
import kakaoSearch from "../redux/slice/KakaoSlice";
import EnjoySlice from "./slice/EnjoySlice";

export const store = configureStore({
  reducer: {
    search: kakaoSearch,
    enjoy: EnjoySlice,
  },
});
