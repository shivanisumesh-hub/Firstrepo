// small JS helper placeholders
function fetchPGs() {
  fetch('/api/pg/all').then(r => r.json()).then(data => console.log('PGs', data));
}
