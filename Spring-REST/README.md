This demo shows how to create simple RESTful services using Spring MVC.

Data is taken from the Rfam READONLY database jdbc:mysql://mysql-rfam-public.ebi.ac.uk:4497/Rfam

From the Rfam website https://rfam.xfam.org/:

"The Rfam database is a collection of RNA families, each represented by multiple sequence alignments, consensus secondary structures and covariance models (CMs)."

I have no clue what the above means, but it's data for sure.

After successfully executing this program, you can see the result at http://localhost:8080/query by default.

A typical response looks like this:

rfam_id	"SNOR75"
auto_wiki	"1264"
description	"Plant small nucleolar RNA SNOR75"
author	"Griffiths-Jones SR"
seed_source	"Griffiths-Jones SR; Plan…database, PMID:12520043"
gathering_cutoff	"50.0"
trusted_cutoff	"50.2"
noise_cutoff	"46.3"
comment	"U15 is a member of the C…pear to be related [4]."
previous_id	"U15"
cmbuild	"cmbuild -F CM SEED"
cmcalibrate	"cmcalibrate --mpi CM"
cmsearch	"cmsearch --cpu 4 --verbo… 549862.597050 CM SEQDB"
num_seed	"6"
num_full	"398"
num_genome_seq	"0"
num_refseq	"0"
type	"Gene; snRNA; snoRNA; CD-box;"
structure_source	"Predicted; PFOLD; Griffiths-Jones SR, Daub J"
number_of_species	"75"
number_3d_structures	"0"
num_pseudonokts	null
tax_seed	""
ecmli_lambda	"0.63346"
ecmli_mu	"-3.18918"
ecmli_cal_db	"1600000"
ecmli_cal_hits	"244518"
maxl	"182"
clen	"88"
match_pair_node	"1"
hmm_tau	"-3.4663"
hmm_lambda	"0.71849"
created	"2014-05-01 10:08:11"
updated	"2019-01-04 15:01:52"
rfam_acc	"RF02539"