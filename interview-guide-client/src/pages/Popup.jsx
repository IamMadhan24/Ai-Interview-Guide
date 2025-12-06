import { useState } from "react";

const Popup = () => {
  const [open, setOpen] = useState(true);

  if (!open) return null;

  return (
    <div className="fixed inset-0 bg-black/80 flex justify-center items-center z-50">
      <div className="bg-white p-6 rounded-xl w-[90%] max-w-sm text-center animate-scaleIn shadow-lg">
        <h3 className="text-lg font-semibold mb-3">Notice</h3>

        <p className="text-gray-700 leading-relaxed">
          Our backend is hosted on a free server and may take 
          <span className="font-semibold"> 30-60 seconds</span> to wake up on first use.
          After that, everything works normally.
        </p>

        <button
          onClick={() => setOpen(false)}
          className="mt-5 px-5 py-2 bg-black text-white rounded-lg hover:bg-gray-900 transition cursor-pointer"
        >
          Continue
        </button>
      </div>
    </div>
  );
};

export default Popup;
