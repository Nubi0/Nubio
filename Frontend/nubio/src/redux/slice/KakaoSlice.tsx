import { createSlice } from "@reduxjs/toolkit";

const kakaoSearchSlice = createSlice({
  name: "kakaoSearch",
  initialState: "" as string,
  reducers: {
    inputKeyword: (state, action) => {
      return action.payload;
    },
  },
});

export const { inputKeyword } = kakaoSearchSlice.actions;
export default kakaoSearchSlice.reducer;