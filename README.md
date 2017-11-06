# AI-Project-2
Music generation into MIDI file using PSO algorithms

Introduction	to	Artificial	Intelligence
Assignment	2
Deadline: 20.11.17	23:59
Output: e-mail	 with	 the	 topic	 “AI	 Assignment	 2” should	 contain	 ZIP	 file	 called	
“NameSurname.zip” with	 code	 (NameSurname.*), report	 (NameSurname.pdf) and	
MIDI-file	(NameSurname.midi) should	sent	to	your	TA:
• Munir	m.makhmutov@innopolis.ru (BS2#1,	BS2#3)
• Hamna	h.aslam@innopolis.ru (BS2#2,	BS2#4)
Programming	language: any	language
Requests:
• Program	must	work,	code	should	be	readable,	should	contain	English	comments
• Report	 should	 contain	 student’s name,	 group,	 open	 answers to	 questions,	
comments	about	the	code.	Also,	chosen	tonality	should	be	mentioned
• NO	extension	of a deadline.	Works	sent	after	deadline	will	NOT	be	evaluated
• NO	plagiarism.	Plagiarized	works	will	not	be	evaluated

Task:	Generate	MIDI	file	which	consists	of	
1) Any sequence	of	chords (tonic,	dominant	and	subdominant	triads) of	the	same	
duration	 (quarters)	 in	 fixed	 tonality (any	 of	 24	 possible	 major	 or	 minor	
tonalities) without	 modulations	 with	 fixed	 tempo (120	 BPM) and	 fixed	 time	
signature (4/4) using	PSO №1
2) Melody	for	this	chord	sequence in	the	same	tonality	with	the	same	tempo	and
time	signature using	PSO	№2. There	should	be	no	chords	inside	melody,	only	
sequence	of	single	notes	with	fixed	duration	(eights)

Number of	bars	should	be	equal	to	4. So,	output	file	should	contain	sequence	of	16	
chords	 (4	chords	per	each	bar) and	32	single	notes	(8	notes	 for	each	bar) which	
should	be	played	simultaneously. Modulation is	change	of	tonality.	Mention	that	
space	between	7	scale	degrees	for	major	and	minor	tonalities	differ (check	lecture).	
Your	fitness	function	should	find	aesthetically	pleasing	combination	of	chords	and	
melody.	 For	 correct	melody	 generation, each	 single	 note	 played	simultaneously	
with	the	chord	should	have	a	value	which	is	by	modulo	12	equal	to one	of	chord’s	
notes.	 So,	for	tonic	 triad in	 C	 major	(60,	 64,	 67) single	 note’s	 values	 should	 be	
60+12*N or	64+12*N or	67+12*N.	N	is	an octave	difference between	chords	and	
melody.	Chords	represent	accompaniment for	melody.	In	this	assignment	consider	
that	accompaniment should	be	lower	than	melody on	1	or	more	octaves. Consider	
that	dissonant	intervals	are	not	aesthetically	pleasing.	Each	interval	has	a	different	
measure	of	aesthetic	enjoyment.	There	should	be	no continuous repetitions of	the	
same	 chords (maximum	 4). Possible	 midi	 note	 values	 are	 in	 the	 range	 [48,96].	
Better	to	use	lower	values	for	chords.	Difference	between	neighbor notes should	
not	exceed	12. Difference	between	lowest notes	of	neighbor chords also	should	
not	exceed	12.
You	 should	 also	 provide	 a	 report	 with	 explanation	 of	 used	 parameters,
representation	 of	 particle	 structures and	 fitness	 function	 explanation for	 both	
PSOs.	Report	 should	contain	used	input values	for	PSOs: particle	amount,	 spent	
time,	generations	used	to	get	the	output.
