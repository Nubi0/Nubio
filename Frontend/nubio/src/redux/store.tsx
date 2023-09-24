import { configureStore } from "@reduxjs/toolkit";
import kakaoSearch from "./slice/MapSlice";
import EnjoySlice from "./slice/EnjoySlice";
import Profileslice from "./slice/Profileslice";
import KakaoSlice from "./slice/MapSlice";

export const store = configureStore({
  reducer: {
    search: kakaoSearch,
    enjoy: EnjoySlice,
    profile: Profileslice,
    kakaoSlice: KakaoSlice,
  },
});
