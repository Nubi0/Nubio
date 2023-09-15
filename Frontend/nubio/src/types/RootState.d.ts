import KakaoSlice from "../redux/slice/KakaoSlice";

export type RootState = {
  search: ReturnType<typeof KakaoSlice>;
};

export default RootState;
