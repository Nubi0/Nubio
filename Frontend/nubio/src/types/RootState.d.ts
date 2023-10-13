import KakaoSlice from "../redux/slice/KakaoSlice";
import EnjoySlice from "../redux/slice/EnjoySlice";

export type RootState = {
  search: ReturnType<typeof KakaoSlice>;
  enjoy: ReturnType<typeof EnjoySlice>;
};

export default RootState;
