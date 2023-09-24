import KakaoSlice from "../redux/slice/MapSlice";
import EnjoySlice from "../redux/slice/EnjoySlice";

export type RootState = {
  search: ReturnType<typeof KakaoSlice>;
  enjoy: ReturnType<typeof EnjoySlice>;
};

export default RootState;
