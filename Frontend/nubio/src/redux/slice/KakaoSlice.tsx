import { createSlice } from "@reduxjs/toolkit";

const kakaoSlice = createSlice({
  name: "kakaoSlice",
  initialState: {
    keyWord: null,
    latitude: null,
    longitude: null,
  },
  reducers: {
    setkeyWord: (state, action) => {
      state.keyWord = action.payload;
    },
    setLatitude: (state, action) => {
      state.latitude = action.payload;
    },
    setLongitude: (state, action) => {
      state.longitude = action.payload;
    },
  },
});

export const { setkeyWord, setLatitude, setLongitude } = kakaoSlice.actions;
export default kakaoSlice.reducer;
