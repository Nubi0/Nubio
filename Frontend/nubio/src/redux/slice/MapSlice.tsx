import { createSlice } from "@reduxjs/toolkit";

const MapSlice = createSlice({
  name: "MapSlice",
  initialState: {
    keyWord: null,
    latitude: null,
    longitude: null,
    safeLatitude: null,
    safeLongitude: null,
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
    setSafeLatitude: (state, action) => {
      state.safeLatitude = action.payload;
    },
    setSafeLongitude: (state, action) => {
      state.safeLongitude = action.payload;
    },
  },
});

export const {
  setkeyWord,
  setLatitude,
  setLongitude,
  setSafeLatitude,
  setSafeLongitude,
} = MapSlice.actions;
export default MapSlice.reducer;
