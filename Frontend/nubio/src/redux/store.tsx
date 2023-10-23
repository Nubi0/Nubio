import { configureStore } from "@reduxjs/toolkit";
import EnjoySlice from "./slice/EnjoySlice";
import Profileslice from "./slice/Profileslice";
import MapSlice from "./slice/MapSlice";
import SignUpSlice from "./slice/SignUpSlice";
import SafeSlice from "./slice/SafeSlice";

export const store = configureStore({
  reducer: {
    enjoy: EnjoySlice,
    profile: Profileslice,
    map: MapSlice,
    signup: SignUpSlice,
    safe: SafeSlice,
  },
});
