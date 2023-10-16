import { createSlice } from "@reduxjs/toolkit";

const SafeSlice = createSlice({
  name: "safeSlice",
  initialState: {
    safePlace: [],
    showShelters: false,
    markerList: [],
    messageMarkerList: [] as any,
    safeMarkerList: [] as any,
    reportPlaces: [],
  },
  reducers: {
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
  setSafePlace,
  setShowShelters,
  setMarkerList,
  setMessageMarkerList,
  setSafeMarkerList,
  setReportPlaces,
} = SafeSlice.actions;
export default SafeSlice.reducer;
