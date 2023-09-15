import { createSlice, configureStore } from "@reduxjs/toolkit";

export const kakaoSearchSlice = createSlice({
  name: "keywordSearch",
  initialState: "",
  reducers: {
    setKeyword: (state, action) => {
      return action.payload;
    },
  },
});

export const store = configureStore({
  reducer: {
    keywordSearch: kakaoSearchSlice.reducer,
  },
});

export const { setKeyword } = kakaoSearchSlice.actions;
