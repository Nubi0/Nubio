import { createSlice } from "@reduxjs/toolkit";

const TmapSearchSlice = createSlice({
  name: "TmapSearch",
  initialState: "" as string,
  reducers: {
    inputKeyword: (state, action) => {
      return action.payload;
    },
  },
});

export const { inputKeyword } = TmapSearchSlice.actions;
export default TmapSearchSlice.reducer;
