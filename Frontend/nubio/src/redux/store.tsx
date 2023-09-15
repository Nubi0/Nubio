import { configureStore } from "@reduxjs/toolkit";
import kakaoSearch from "../redux/slice/KakaoSlice";

export const store = configureStore({
  reducer: {
    search: kakaoSearch,
  },
});
