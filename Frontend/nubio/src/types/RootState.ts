// RootState.ts 파일을 생성하여 Redux 스토어의 전체 상태를 정의합니다.
import { combineReducers } from "@reduxjs/toolkit";
import { kakaoSearchSlice } from "../redux/slice/KakaoMap";

const rootReducer = combineReducers({
  keywordSearch: kakaoSearchSlice.reducer,
  // 다른 슬라이스 추가 가능
});

export type RootState = ReturnType<typeof rootReducer>;
