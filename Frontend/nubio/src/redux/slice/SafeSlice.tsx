import { createSlice } from "@reduxjs/toolkit";

const SafeSlice = createSlice({
  name: "SafeSlice",
  initialState: {
    shortTime: null,
    safeTime: null,
    safePlace: [],
    showShelters: false,
    markerList: [],
    messageMarkerList: [] as any,
    safeMarkerList: [] as any,
    reportPlaces: [],
  },
  reducers: {
    setShortTime: (state, action) => {
      state.shortTime = action.payload;
    },
    setSafeTime: (state, action) => {
      state.safeTime = action.payload;
    },
    setSafePlace: (state, action) => {
      state.safePlace = action.payload;
    },
    setShowShelters: (state, action) => {
      state.showShelters = action.payload;
    },
    setMarkerList: (state, action) => {
      state.markerList = action.payload;
    },
    setMessageMarkerList: (state, action) => {
      const payloads = Array.isArray(action.payload)
        ? action.payload
        : [action.payload];
      state.messageMarkerList.push(...payloads);
    },
    setSafeMarkerList: (state, action) => {
      const payloads = Array.isArray(action.payload)
        ? action.payload
        : [action.payload];
      state.safeMarkerList.push(...payloads);
    },
    setReportPlaces: (state, action) => {
      state.reportPlaces = action.payload;
    },
  },
});
export const {
  setShortTime,
  setSafeTime,
  setSafePlace,
  setShowShelters,
  setMarkerList,
  setMessageMarkerList,
  setSafeMarkerList,
  setReportPlaces,
} = SafeSlice.actions;
export default SafeSlice.reducer;
