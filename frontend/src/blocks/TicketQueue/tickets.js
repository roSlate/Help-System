//isto vai ser substituido por uma chamada a API para pegar os tickets do banco de dados
export const sampleTickets = [
  { id: 'HD-2841', title: 'Aero glass reflection not rendering correctly on client portal', requester: 'Marcus Vance (AeroCorp)', category: 'UI/Theme Glitch', time: '10m ago', status: 'Open' },
  { id: 'HD-2839', title: 'Leaf badge status indicators fail to sync with API database', requester: 'Elena Rostova (GreenTech)', category: 'Backend Sync', time: '42m ago', status: 'In Progress' },
  { id: 'HD-2832', title: 'Requesting installer file for legacy Windows Vista theme patch', requester: 'Tariq Johnson', category: 'Downloads', time: '1h ago', status: 'Resolved' },
  { id: 'HD-2819', title: 'Translucency effect causing heavy performance lag on older clients', requester: 'Sven Lindqvist', category: 'Performance Optimization', time: '3h ago', status: 'In Progress' },
  { id: 'HD-2802', title: 'Dewdrop splash screen animation does not dismiss upon startup completion', requester: 'Fiona Gallagher', category: 'Visual Effects', time: 'Yesterday', status: 'Resolved' },
]

export const statusClass = {
  'Open': 'status-open',
  'In Progress': 'status-progress',
  'Resolved': 'status-resolved',
}
